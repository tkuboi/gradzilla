import subprocess
import sys
import os 
import re

def parseout_score(output):
    score = 0
    expression = r"Your code has been rated at (-?\d+.?\d*)"
    pattern = re.compile(expression)
    #do pattern matching with regex
    match = pattern.search(output)
    #if the match is found
    if match:
        #update the score
        score = float(match.group(1))
    #return the score
    return score

def get_files(directory):
    """return the list of files in a directory
    Args:
        directory (str): the directory
    Returns:
        list : a list of files
    """
    files = []
    for item in os.listdir(directory):
        item = os.path.join(directory, item)
        if not os.path.isfile(item):
            continue
        parts = os.path.splitext(item)
        if parts and len(parts) > 1 and parts[1] == '.py'\
            and parts[0].lower().find("test") == -1:
            files.append(item)
    return files

def run(py):
    cmd = ['python3', '-m', 'pylint', py]
    process = subprocess.run(
        cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    print(process.stdout)
    return process.stdout

def run_lint(files):
    """
    Args:
        files (list): a list of files
    Returns:
        list : a list of outputs(str)
    """
    outputs = []
    for fname in files:
        if "test" in fname:
            continue
        outputs.append(run(fname))
    return outputs

def average_scores(outputs):
    """
    Args:
        outputs (list): a list of str
    Returns:
        int : average score
    """
    score = 0
    num_files = 0
    for output in outputs:
        pscore = parseout_score(output)
        if pscore:
            score += max(0, pscore)
            num_files += 1
    return round(score / max(1,num_files), 0)

def main():
    dir_name = sys.argv[1] 
    #get a list of files in the current directory
    files = get_files(dir_name)
    #run pylint and average the score
    outputs = run_lint(files)
    #parse the output
    score = average_scores(outputs)
    #print the score
    print("SCORE:{%s}\n" % (int(score)))
    #print the output
    #print(outputs)
    #return the score
    return score

if __name__ == '__main__':
    main()
