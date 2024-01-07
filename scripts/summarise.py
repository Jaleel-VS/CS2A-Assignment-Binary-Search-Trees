'''
Opens the insertion and lookup results and collects relevant data from them to 
find the minimum, maximum and average
'''

from base_class import *

assignment_root_directory = str(pathlib.Path(__file__).parents[1])


def get_operations(file_path):
    '''
    Loops through each line and finds the number of operation per query
    '''
    operations = []
    with open(file_path.name) as raw_data:
        entries = raw_data.readlines()

        for entry in entries:
            if 'operation' in entry:
                operations.append(int(re.findall('([^\s]+)', entry)[0])) # find first empty space
    
    return operations


def create_summary() -> None:
    '''
    Calculates the minimum, max and average
    '''
    pbar = tqdm(total=100)
    for stat in tqdm(['lookup', 'insertion']):
        summary = open(f'{assignment_root_directory}/output/part_2/summary_{stat}.csv', 'w')
        summary.write('n,ds,min,max,avg\n')
        for ds in ['array', 'bst']:
            for n in range(991,9920, 991):
                with open(f'{assignment_root_directory}/output/part_2/operations/{ds}_{stat}_results_subset_{n}.txt') as raw_data:
                    results = get_operations(raw_data)
                    summary.write(f'{n},{ds},')
                    summary.write(f'{min(results)},')
                    summary.write(f'{max(results)},')
                    summary.write(f'{sum(results) / len(results):.2f}\n')
                    pbar.update(2.5)

        summary.close()
    pbar.close()




if __name__ == '__main__':
    create_summary()
