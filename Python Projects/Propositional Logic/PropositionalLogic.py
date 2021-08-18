# CS220 - Programming Assignment 1 : Boolean Logic
# author - Zachary Klausner  (zachklau@rams.colostate.edu)

# NOTE:
# You must use small single letters for your variable names, for eg. a, b, c
# You may use parenthesis to group your expressions such as 'a and (b or c)'

# Implement the following four functions:
# truth_table, count_satisfying, is_tautology and are_equivalent

# Submission:
# Submit this file using the checkin system on the course web page.



######## Do not modify the following block of code ########
# ********************** BEGIN *******************************

from functools import partial
import re
import itertools


class Infix(object):
    def __init__(self, func):
        self.func = func
    def __or__(self, other):
        return self.func(other)
    def __ror__(self, other):
        return Infix(partial(self.func, other))
    def __call__(self, v1, v2):
        return self.func(v1, v2)

@Infix
def implies(p, q) :
    return not p or q

@Infix
def iff(p, q) :
    return (p |implies| q) and (q |implies| p)


# You must use this function to extract variables
# This function takes an expression as input and returns a sorted list of variables
# Do NOT modify this function
def extract_variables(expression):
    sorted_variable_set = sorted(set(re.findall(r'\b[a-z]\b', expression)))
    return sorted_variable_set

# *********************** END ***************************




############## IMPLEMENT THE FOLLOWING FUNCTIONS  ##############
############## Do not modify function definitions ##############


# This function calculates a truth table for a given expression
# input: expression
# output: truth table as a list of lists
# You must use extract_variables function to generate the list of variables from expression
# return a list of lists for this function
def truth_table(expression):
   
    r_truth_table = []
   
    variables = extract_variables(expression)
    
    my_list = list(itertools.product([0, 1], repeat = len(variables)))
    
    for sub_lst in my_list:
        
        row = []
        
        for i in range(0, len(sub_lst)):
            
            if sub_lst[i] == 0:
                
                row.append(True)
                exec(str(variables[i])+'=True', globals(), locals())
            
            else:
                
                row.append(False)
                exec(str(variables[i])+'=False', globals(), locals())
            
        row.append(eval(expression))  
        r_truth_table.append(row)
       
    return r_truth_table

# count the number of satisfying values
# input: expression
# output: number of satisfying values in the expression
def count_satisfying(expression):
    
    table = truth_table(expression)
    my_list = get_result_list(table)
    
    truth_count = 0
    
    for entry in my_list:
        if entry == True:
            truth_count += 1
    
    return truth_count
        
    

# if the expression is a tautology return True,
# False otherwise
# input: expression
# output: bool
def is_tautology(expression):
    
    table = truth_table(expression)
    my_list = get_result_list(table)
    
    for entry in my_list:
        if entry == False:
            return False
        
    return True

# if expr1 is equivalent to expr2 return True,
# False otherwise
# input: expression 1 and expression 2
# output: bool
def are_equivalent(expr1, expr2):
    
    table_1 = truth_table(expr1)
    table_2 = truth_table(expr2)
    
    my_list_1 = get_result_list(table_1)
    my_list_2 = get_result_list(table_2)
    
    for entry_1, entry_2 in zip(my_list_1, my_list_2):
        if entry_1 != entry_2:
            return False
    
    return True

def get_result_list(table):
    
    return_list = []
    
    for row in table:
        return_list.append(row[2])
    
    return return_list