package net.tkuboi.gradzilla.grader;

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

public class GraderService {
  private List<Grader> graders;
  private String workDir;
  private static final long timeout = 60000;

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

  public GraderService(List<Grader> graders) {
    this.graders = graders;
    this.workDir = null;
  }

  public GraderService(List<Grader> graders, String workDir) {
    this.graders = graders;
    this.workDir = workDir;
  }

  public GraderResultDto grade(Path targetPath) {
    String result = "";
    int score = 0;
    try {
      unpack(targetPath);

      String subDir = targetPath.getParent().toString();
      for (Grader grader: graders) {
        String filename = Paths.get(grader.getFilePath()).getFileName().toString();
        Path testFile = Paths.get(grader.getFilePath());
        Process proc = null;
        String[] cmd = null;
        Path workDir = null;
        if (grader.getCopy().equals(1)) {
          Path target = Paths.get(subDir + "/" + filename);
          Files.copy(Paths.get(grader.getFilePath()), target, REPLACE_EXISTING);
          workDir = targetPath.getParent();
          cmd = new String[] {grader.getProgram(), target.toString()};
          proc = Runtime.getRuntime()
            .exec(cmd, null, workDir.toFile());
        } else {
          workDir = testFile.getParent();
          cmd = new String[] {grader.getProgram(), testFile.toString(), targetPath.getParent().toString()};
          proc = Runtime.getRuntime()
            .exec(cmd, null, workDir.toFile());
        }

        Worker worker = new Worker(proc);
        worker.start();
        try {
          worker.join(timeout);
          System.out.println("Exit Code: " + worker.exit);
          if (worker.exit == 0) {
            GraderResultDto resultDto = getOutput(proc);
            result += resultDto.getResult();
            score += resultDto.getScore();
          }
          else if (worker.exit == null) {
            throw new TimeoutException();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          proc.destroyForcibly();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
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
}
