# TODO: implement debugging features

# Colors :D #
RED='\033[0;31m'
BLUE='\033[0;34m'
GREEN='\033[0;32m'
RESET='\033[0m' 
BOLD=$(tput bold)
NORM=$(tput sgr0)

PrintNegative () {
    echo -e "${BOLD}[${RED}-${RESET}${BOLD}]${NORM} $1"
}

PrintPositive () {
    echo -e "${BOLD}[${GREEN}+${RESET}${BOLD}]${NORM} $1"
}

PrintNeutral () {
    echo -e "${BOLD}[${BLUE}o${RESET}${BOLD}]${NORM} $1"
}

Run () {
    PrintNeutral "Running program...\n"
    java -cp ./build dev/frankovich/sim/Simulation $@
    echo -ne "\n"
    PrintNeutral "Program exited with exit code $?"
}

Clean () {
    PrintNeutral "Cleaning build directory..."
    rm -rf ./build/*
    PrintPositive "Cleaned!"
}

Compile () {
    PrintNeutral "Compiling..."
    javac -d ./build -sourcepath ./src -cp ./build ./src/dev/frankovich/sim/Simulation.java
    if [ $? != 0 ]; then
        PrintNegative "Failed compiling!"
        exit 0
    else
        PrintPositive "Compiled!"
    fi
}

# Cleans out all the class files in the build directory
if [ "$1" == "clean" ]; then
    Clean
    exit 0
fi

# Just compiles and exits
if [ "$1" == "compile" ]; then
    Compile
    exit 0
fi

# Compiles and runs program with args given
Clean
Compile
Run $@
