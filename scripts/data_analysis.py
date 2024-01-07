'''
This class is responsable for the analysis and exportation of 
the data to a readable format
'''
from base_class import *

root_directory = str(pathlib.Path(__file__).parents[1])


class DataAnalysis:

    def __init__(self) -> None:
        self.df_insert = pd.read_csv(f'{root_directory}/output/part_2/summary_insertion.csv')
        self.df_insert.set_index('n', inplace=True)
        self.df_lookup = pd.read_csv(f'{root_directory}/output/part_2/summary_lookup.csv')
        self.df_lookup.set_index('n', inplace=True)

    @staticmethod
    def __rename_columns(d) -> pd.DataFrame:
        new_df = d
        cols = {'x': 'array', 'y': 'bst'}

        for col in new_df.columns:
            suffix = col[-2:]
            new_df.rename(columns={col: f"{col.replace(suffix, '')}_{cols[suffix[-1]]}"}, inplace=True)

        new_df.drop(['ds_array', 'ds_bst'], axis=1, inplace=True)
        return new_df

    def __clean_up_data(self) -> None:
        self.df_lookup = pd.merge(self.df_lookup[self.df_lookup.ds == "array"],
                                self.df_lookup[self.df_lookup.ds == "bst"],
                                on='n')

        self.df_insert = pd.merge(self.df_insert[self.df_insert.ds == "array"],
                            self.df_insert[self.df_insert.ds == "bst"],
                            on='n')

        self.df_lookup = self.__rename_columns(self.df_lookup)
        self.df_insert = self.__rename_columns(self.df_insert)


    def __save_plots(self, the_df: pd.DataFrame, stat: str) -> None:
        fig = the_df.plot(logy=False, ylabel='Operations',  title=f'Array vs Binary Search Tree: {stat.title()} time per n')
        plt.legend(loc='best')
        fig.figure.savefig(f"{root_directory}/output/part_2/graph_{stat}.jpg", dpi=300) 

    
    def __export_table(self, df, file_name, title) -> None:
        fig = plt.figure(facecolor='w', edgecolor='k')
        sns.heatmap(df, annot=True, cmap='viridis', cbar=False,  fmt='g')
        plt.tick_params(labelbottom=False, labeltop=True,bottom=False, top=True)
        plt.title(title)
        plt.yticks(rotation=0) 
        plt.savefig(f'{root_directory}/output/part_2/{file_name}.jpg', dpi=300)

    def run_analysis(self) -> None:
        pbar = tqdm(total=100)
        self.__clean_up_data()
        pbar.update(20)
        self.__export_table(self.df_insert, 'insert_table', 'Insert operations per n')
        pbar.update(20)
        self.__export_table(self.df_lookup, 'lookup_table', 'Lookup operations per n')
        pbar.update(20)
        self.__save_plots(self.df_insert, 'insert')
        pbar.update(20)
        self.__save_plots(self.df_lookup, 'lookup')
        pbar.update(20)


if '__main__' == __name__:
    da = DataAnalysis()
    da.run_analysis()


    


















