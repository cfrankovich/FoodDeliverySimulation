# NAME
fdsim - a food delivery simulation 

# SYNOPSIS
**fdsim** [OPTION...]

# DESCTIPTION
**fdsim** is a custom food delivery simulation allowing for users to produce reports of any type of simulation. export time values are all minutes since the simulation started. 

# OPTIONS
## Map Tools ##
**--create-map**
    Creates a map
**--map=**_FILE_
    Uses a map defined by the user
**--menu=**_FILE_
    Uses a menu defined by the user
## Simulation Settings ##
**--use-settings=FILE**
    Uses settings from file given. See notes for file formatting.
**--order-frequency=FREQUENCY**
    Orders come in as a result of the percentage given. Values not between 0 and 1 won't work.
**--price-per-gallon=PRICE**
    Price per gallon of gas.
**--driver-salary=SALARY**
    Salary for drivers.
**--num-drivers=NUM**
    Number of drivers operating at once.
**--start-time=TIME**
    Start time of the simulation. See notes for formatting.
**--end-time=TIME**
    End time of the simulation. See notes for formatting.
**--max-orders=ORDERS**
    Will only simulate until number of orders given.

# NOTES
## Settings File ##
The format for the settings file should follow the example in the repository. (simsettings.dat)
## Times Format ##
Times must be given as an integer form of 24 hour time. Only the hour. Examples are 9, 23, 12, 5.
## Map Creation Tool ##
Icons not working in map creation tool. The buttons each have numbers on them corresponding to these values ->
0 :>: Save Map to "newmap.txt"
1 :>: tbd 
2 :>: inspect tool (not implemented)
3 :>: house tool
4 :>: restaurant tool
5 :>: road tool
6 :>: delete tool

# BUGS
- Icon trouble in map creation tool

# EXAMPLE
fdsim --map=newmap.txt --menu=menuitems.csv