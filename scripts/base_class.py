'''
Python file that contains all the imports for all the scripts to prevent having to reuse them
'''


from tqdm import tqdm # a progress bar
import pandas as pd # for manipulating data
import matplotlib.pyplot as plt # for plotting data
import pathlib # to handle directories
import seaborn as sns # to export tables
import re # for string manipulation
import random # for generating random values
import os # for running shell commands
import csv 

class BaseClass:
    def __init__(self) -> None:
        pass