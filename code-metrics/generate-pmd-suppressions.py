import fileinput
import json
import re

json_input = ''.join([line for line in fileinput.input()])

pmd_violations = json.loads(json_input)

for file in pmd_violations.get('files'):
    filename = file.get('filename')
    cls = re.sub('.*src/main/java/|\.java', '', filename).replace('/', '.')

    rules = [violation.get('rule') for violation in file.get('violations')]
    concatenated_rules = ','.join(rules)

    print(f'{cls}={concatenated_rules}')
