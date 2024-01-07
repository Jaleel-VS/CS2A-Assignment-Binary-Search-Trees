'''
Script that generates three random test sets for part 1 of the assignment
'''

from base_class import *

assignment_root_directory = str(pathlib.Path(__file__).parents[1])

fake_country_names = [
    "Planet Namek",
    "Kuvukiland",
    "Ancient Greece",
    "Atlantis",
    "Stonehenge",
    "Valhalla",
    "Midgard",
    "Tutankhamun",
    "Kalahari",
    "Simba",
    "Narnia",
]

def generate_random_data(csv_lines):
    '''generate a list of countries with length 1 - 100'''
    countries = set() # used a set to avoid duplicates
    for _ in  range(random.randint(10, 30)):
        random_number =  random.randint(0, 9918)
        
        if random_number % 6 == 0: # arbirtraliy add a fake country to the list
            countries.add(random.choice(fake_country_names))
        
        countries.add(csv_lines[random_number][0])    

    return countries

def export_data(input_data):
    for i in tqdm(range(1, 4)):
        countries = generate_random_data(input_data)
        with open(f'{assignment_root_directory}/input/test_set_{i}.txt', 'w', encoding='utf-8') as new_file:
                new_file.write(input_data[random.randint(0, 9918)][1] + '\n') # random date
                new_file.writelines(f'{country}\n' for country in countries)
                new_file.write('\n')


def run():
    try:
        with open(assignment_root_directory + "/input/vaccinations.csv") as vaccination_data:
            csv_reader = csv.reader(vaccination_data, delimiter=",")
            countries_data = [country for country in csv_reader]
            export_data(countries_data)
            print("Part 1 test data generated successfully")
            
    except (FileNotFoundError):
        print(f'The expected file directory is {assignment_root_directory}/input/vaccinations.csv. Please make sure the file is located there.')

    