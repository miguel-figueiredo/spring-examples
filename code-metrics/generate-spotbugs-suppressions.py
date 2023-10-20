import fileinput
import json
import re

json_input = ''.join([line for line in fileinput.input()])

spotbugs_violations = json.loads(json_input)

print('<FindBugsFilter>')

for result in spotbugs_violations.get('runs')[0].get('results'):
    bug = result.get('ruleId')
    filename = result.get('locations')[0].get('physicalLocation').get('artifactLocation').get('uri')
    cls = re.sub('.*src/main/java/|\.java', '', filename).replace('/', '.')

    print(f'<Match><Class name="{cls}"/><Bug pattern="{bug}"/></Match>')

print('</FindBugsFilter>')