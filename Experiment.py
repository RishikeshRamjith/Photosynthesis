import os

#Automated running of parallel program with changing dataset sizes and saving results to TimingsParallel.txt
for i in range(10000, 1000000, 10000):
    message = "Data size "+str(i)
    #print(message)
    os.system("echo "+message+" >> TimingsParallel.txt")
    argument = "java SimulationParallel sample_input.txt OutputParallel.txt "+str(i)+" >> TimingsParallel.txt"
    #print(argument)
    os.system(argument)
    print("\n")

#Automated running of parallel program with changing dataset sizes and saving results to TimingsSequential.txt
for i in range(10000, 1000000, 10000):
    message = "Data size "+str(i)
    #print(message)
    os.system("echo "+message+" >> TimingsParallel.txt")
    argument = "java SimulationSequential sample_input.txt OutputSequential.txt "+str(i)+" >> TimingsSequential.txt"
    #print(argument)
    os.system(argument)
    print("\n")
