# TODO: implement debugging features

# Cleans out all the class files in the build directory
if [ "$1" == "clean" ]; then
    echo "[o] Cleaning build directory..."
    rm -rf ./build/*
    echo "[+] Cleaned!"
    exit 0
fi

# Just compiles and exits
if [ "$1" == "compile" ]; then
    echo "[o] Compiling..."
    javac -d ./build -sourcepath ./src -cp ./build ./src/dev/frankovich/sim/Simulation.java
    if [ $? != 0 ]; then
        echo "[-] Failed compiling!"
        exit 0
    else 
        echo "[+] Finished compiling!" 
    fi
    exit 0
fi

# Compiles and runs program with args given
echo "[o] Cleaning build directory..."
rm -rf ./build/*
echo "[+] Cleaned!"
echo "[o] Compiling..."
javac -d ./build -sourcepath ./src -cp ./build ./src/dev/frankovich/sim/Simulation.java
if [ $? != 0 ]; then
        echo "[-] Failed compiling!"
        exit 0
else
    echo "[+] Finished compiling!" 
fi
java -cp ./build dev/frankovich/sim/Simulation $@
echo "[o] Program exited with exit code $?"