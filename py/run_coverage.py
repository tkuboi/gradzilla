from coverage import Coverage
import coverage
import sys
import subprocess
import re

OUTOF = 20

def run(cmd):
    process = subprocess.run(
        cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    return process

def print_score(covered, outof):
    score = round(0.01 * outof * covered, 0)
    print("SCORE:{%s}\n" % (int(score)))

def extract_covered(outstr):
    m = re.search(r"""TOTAL\s*\d*\s*\d*\s*(\d+)%""", outstr)
    if m:
        if len(m.groups()) >= 1:
            return float(m.group(1))
    return 0

def main():
    tester = sys.argv[1]
    cmd = ['python3', '-m', 'coverage', 'erase']
    proc = run(cmd)
    cmd = ['python3', '-m', 'coverage', 'run', tester]
    proc = run(cmd)
    cmd = ['python3', '-m', 'coverage', 'report', '-m']
    proc = run(cmd)
    print("Test Coverage")
    print(proc.stdout)
    covered = extract_covered(proc.stdout)
    print_score(covered, OUTOF)

if __name__ == '__main__':
    main()
