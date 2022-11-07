import os
import datetime

directory = os.getcwd() 
ext = '.java'
cbedge = '// ******************************************************'
print('- CS 225 Comment Block Generator -')
studentname = input('Name: ')
date = input('Date (leave blank for auto): ')
if date == '':
    date = datetime.date.today() 
print()

class Attribute:
    def __init__(self, scope, type, name):
        if scope == 'private':
            self.scope = True
        else:
            self.scope = False
        self.type = type
        self.name = name 
    def getuml(self):
        if self.scope:
            return f'-{self.name}: {self.type}'
        else:
            return f'+{self.name}: {self.type}'
        

class Method:
    def __init__(self, scope, returntype, name, params):
        if scope == 'private':
            self.scope = True
        else:
            self.scope = False
        self.type = returntype
        self.name = name 
        self.params = params
    def getuml(self):
        pms = ' '
        for i in range(0, len(self.params)):
            if i != len(self.params)-1: 
                pms += f'{self.params[i]}, '
            else:
                pms += f'{self.params[i]} '
        if self.scope:
            return f'-{self.name}({pms}): {self.type}'
        else:
            return f'+{self.name}({pms}): {self.type}'  

def recursivelistdir(directory):
    files = []
    for filename in os.listdir(directory):
        f = os.path.join(directory,filename)
        if os.path.isfile(f):
            files.append(f)
        elif os.path.isdir(f):
            for file in recursivelistdir(f):
                files.append(file)
    return files

def generatecommentblock(file, attributes, methods):
    classname = (file.split('\\')[-1:])[0].split('.')[0]
    commentblock = cbedge + '\n'
    commentblock += f'// Class:\t\t\t{classname}\n'
    commentblock += f'// Name:\t\t\t{studentname}\n' 
    commentblock += f'// Date:\t\t\t{date}\n'
    commentblock += '//\n'
    purpose = input(f'Purpose for class "{classname}": ')
    commentblock += f'// Purpose:\t\t\t{purpose}\n//\n'
    commentblock += f'// Attributes:\t\t' 
    for a in attributes:
        commentblock += a.getuml() + '\n//\t\t\t\t\t'
    commentblock += f'\n// Methods:\t\t\t'
    for m in methods:
        commentblock += m.getuml() + '\n//\t\t\t\t\t'
    commentblock += '\n' + cbedge + '\n\n'
    return commentblock

def getparams(line):
    ps = []
    openpidx = line.index('(')
    clospidx = line.index(')')
    spl = line[openpidx + 1 : clospidx].split()
    for i in range(0, len(spl), 2):
        ps.append(spl[i])
    return ps

def getattributesandmethods(file):
    atts = []
    mths = []
    f = open(file, 'r')
    lines = f.readlines()
    for line in lines:
        if line.__contains__('private') or line.__contains__('public'):
            if line.__contains__('class') or line.__contains__('final'):
                continue
            elif line.__contains__('('): 
                spl = line.split()
                if spl[1].__contains__('('):
                    continue
                name = spl[2].split('(')[0]
                params = getparams(line)
                mths.append(Method(spl[0], spl[1], name, params))
            else:
                spl = line.split()
                atts.append(Attribute(spl[0], spl[1], (spl[2])[:-1]))
    return (atts, mths) 

files = [f for f in recursivelistdir(directory) if f.endswith(ext)]

for file in files:
    atsandmts = getattributesandmethods(file)
    block = generatecommentblock(file, atsandmts[0], atsandmts[1])
    f = open(file, 'r')
    everythinglol = f.readlines()
    f.close()
    with open(file, 'w') as f:
        f.write(block)
        for line in everythinglol:
            f.write(line)

