package net.tkuboi.gradzilla.grader;

import net.tkuboi.gradzilla.grade.Grade;
import net.tkuboi.gradzilla.grade.GradeId;
import net.tkuboi.gradzilla.grade.GradeRepository;
import net.tkuboi.gradzilla.submission.Submission;
import net.tkuboi.gradzilla.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class GraderService {
  private static final long timeout = 60000;
  private final SubmissionRepository submissionRepository;
  private final GradeRepository gradeRepository;
  private final GraderRepository graderRepository;

  private static class Worker extends Thread {
    private final Process process;
    private Integer exit;
    private Worker(Process process) {
      this.process = process;
    }
    public void run() {
      try {
        exit = process.waitFor();
      } catch (InterruptedException ignore) {
        return;
      }
    }
  }

  @Autowired
  public GraderService(SubmissionRepository submissionRepository, GradeRepository gradeRepository, GraderRepository graderRepository) {
    this.submissionRepository = submissionRepository;
    this.gradeRepository = gradeRepository;
    this.graderRepository = graderRepository;
  }

  public GraderResultDto grade(String course, Submission submission) {
    List<Grader> graders = graderRepository.findAllByAssignmentOrderBySeq(submission.getAssignment());
    String result = "";
    int score = 0;
    Path targetPath = Paths.get(submission.getFilePath());
    try {
      unpack(targetPath);

      String subDir = targetPath.getParent().toString();
      for (Grader grader: graders) {
        String filename = Paths.get(grader.getFilePath()).getFileName().toString();
        Path testFile = Paths.get(grader.getFilePath());
        Process proc = null;
        String[] cmd = null;
        Path rootDir = null;
        if (grader.getCopy().equals(1)) {
          Path target = Paths.get(subDir + "/" + filename);
          Files.copy(Paths.get(grader.getFilePath()), target, REPLACE_EXISTING);
          rootDir = targetPath.getParent();
          cmd = new String[] {grader.getProgram(), target.toString()};
          proc = Runtime.getRuntime()
            .exec(cmd, null, rootDir.toFile());
        } else {
          rootDir = testFile.getParent();
          cmd = new String[] {grader.getProgram(), testFile.toString(), targetPath.getParent().toString()};
          proc = Runtime.getRuntime()
            .exec(cmd, null, rootDir.toFile());
        }

        Worker worker = new Worker(proc);
        worker.start();
        try {
          worker.join(timeout);
          System.out.println("Exit Code: " + worker.exit);
          if (worker.exit != null) {
            GraderResultDto resultDto = getOutput(proc);
            result += resultDto.getResult();
            if (worker.exit == 0) score += resultDto.getScore();
          }
          else {
            throw new TimeoutException();
          }
          //Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          proc.destroyForcibly();
        }
      }
      storeResult(course, submission, score, result);
    } catch (IOException e) {
      e.printStackTrace();
      result = e.getMessage();
    } catch (TimeoutException e) {
      e.printStackTrace();
      result = e.getMessage();
    } catch (Exception e) {
      e.printStackTrace();
      result = e.getMessage();
    }
    System.out.println(result);
    return new GraderResultDto(result, score);
  }

  private GraderResultDto getOutput(Process proc) {
    GraderResultDto resultDto = new GraderResultDto();
    StringBuilder output = new StringBuilder();
    Pattern regex = Pattern.compile("SCORE:\\{(\\d+)\\}");
    try {
      BufferedReader stdInput = new BufferedReader(new
        InputStreamReader(proc.getInputStream()));

      BufferedReader stdError = new BufferedReader(new
        InputStreamReader(proc.getErrorStream()));

      // Read the output from the command
      System.out.println("Here is the standard output of the command:\n");
      String line = null;
      while ((line = stdInput.readLine()) != null) {
        System.out.println(line);
        Matcher m = regex.matcher(line);
        if (m.find()) {
          resultDto.setScore(Integer.parseInt(m.group(1)));
        } else {
          output.append(line);
          output.append("<br>\n");
        }
      }

      // Read any errors from the attempted command
      System.out.println("Here is the standard error of the command (if any):\n");
      while ((line = stdError.readLine()) != null) {
        System.out.println(line);
        output.append(line);
        output.append("<br>\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    resultDto.setResult(output.toString());
    return resultDto;
  }

  private void unpack(Path targetPath) throws IOException {
    if (targetPath.getFileName().toString().endsWith(".zip")) {
      String[] zipcmd = new String[] {"unzip", targetPath.toString()};
      Runtime.getRuntime().exec(zipcmd, null, targetPath.getParent().toFile());
    }
  }

  private void storeResult(String course, Submission submission, int score, String result) {
    GradeId id = new GradeId(submission.getUser(), submission.getAssignment());
    Grade grade = gradeRepository.findById(id);
    if (grade != null) {
      if (score > grade.getGrade().intValue()) {
        grade.setGrade(score);
      }
    }
    else {
      grade = new Grade(id, course, score);
    }
    gradeRepository.save(grade);

    submission.setResult(result);
    submission.setScore(score);
    submission.setStatus(Submission.Status.GRADED.toString());
    submissionRepository.save(submission);
  }
}
