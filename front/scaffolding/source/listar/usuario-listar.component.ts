/**
 * Componente de listagem de #MODULO#s.
 *
 * @author Pojeto
 * @since 0.0.0
 */

/*#import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { #MODULO_CAP#, #MODULO_CAP#Service } from '../shared';

@Component({
	selector: 'pojeto-#MODULO#-listar',
	templateUrl: './#MODULO#-listar.component.html',
	styleUrls: ['./#MODULO#-listar.component.css']
})
export class #MODULO_CAP#ListarComponent implements OnInit {

	public page: number = 1;
	public itemsPerPage: number = 10;
	public maxSize: number = 5;
	public numPages: number = 10;
	public length: number = 0;
	private data: Array<any>;

	private #MODULO#s: #MODULO_CAP#[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;#*/

	/**
	 * Construtor.
	 *
	 * @param #MODULO_CAP#Service #MODULO#Service
	 */
	/*#constructor(private #MODULO#Service: #MODULO_CAP#Service,
		private route: ActivatedRoute) {
	}#*/


	/*#public rows: Array<any> = [];#*/
	/*#public columns: Array<any> = [
		{
			title: 'Nome',
			name: 'name',
			filtering: { filterString: '', placeholder: 'Filtrar por nome' }
		},
		{
			title: 'Imagem URL',
			name: 'imgUrl',
			sort: false,
			filtering: { filterString: '', placeholder: 'Filtrar Imagem URL' }
		},
		{
			title: 'Nome Abragente',
			name: 'abbrName',
			filtering: { filterString: '', placeholder: 'Filtrar por Nome Abragente' }

		}
	];#*/


	/*#public config: any = {
		paging: true,
		sorting: { columns: this.columns },
		filtering: { filterString: '' },
		className: ['table-striped', 'table-bordered']
	}#*/

	/**
	 * Método executado logo após a criação do componente.
	 */
	/*#ngOnInit(): void {
		this.onChangeTable(this.config);
	}#*/

	/*#public changePage(page: any, data: Array<any> = this.data): Array<any> {
		let start = (page.page - 1) * page.itemsPerPage;
		let end = page.itemsPerPage > -1 ? (start + page.itemsPerPage) : data.length;
		return data.slice(start, end);
	}#*/


	/*#public changeSort(data: any, config: any): any {
		if (!config.sorting) {
			return data;
		}

		let columns = this.config.sorting.columns || [];
		let columnName: string = void 0;
		let sort: string = void 0;

		for (let i = 0; i < columns.length; i++) {
			if (columns[i].sort !== '' && columns[i].sort !== false) {
				columnName = columns[i].name;
				sort = columns[i].sort;
			}
		}

		if (!columnName) {
			return data;
		}

		// simple sorting
		return data.sort((previous: any, current: any) => {
			if (previous[columnName] > current[columnName]) {
				return sort === 'desc' ? -1 : 1;
			} else if (previous[columnName] < current[columnName]) {
				return sort === 'asc' ? -1 : 1;
			}
			return 0;
		});
	}#*/

	/*#public changeFilter(data: any, config: any): any {
		let filteredData: Array<any> = data;
		this.columns.forEach((column: any) => {
			if (column.filtering) {
				filteredData = filteredData.filter((item: any) => {
					return item[column.name].match(column.filtering.filterString);
				});
			}
		});

		if (!config.filtering) {
			return filteredData;
		}

		if (config.filtering.columnName) {
			return filteredData.filter((item: any) =>
				item[config.filtering.columnName].match(this.config.filtering.filterString));
		}

		let tempArray: Array<any> = [];
		filteredData.forEach((item: any) => {
			let flag = false;
			this.columns.forEach((column: any) => {
				if (item[column.name].toString().match(this.config.filtering.filterString)) {
					flag = true;
				}
			});
			if (flag) {
				tempArray.push(item);
			}
		});
		filteredData = tempArray;

		return filteredData;
	}#*/


	/*#public onChangeTable(config: any, page: any = { page: this.page, itemsPerPage: this.itemsPerPage }): any {
		if (config.filtering) {
			Object.assign(this.config.filtering, config.filtering);
		}

		if (config.sorting) {
			Object.assign(this.config.sorting, config.sorting);
		}

		this.data = this.#MODULO#Service.listarTodos();
		//this.length = this.data.length;
		let filteredData = this.changeFilter(this.data, this.config);
		let sortedData = this.changeSort(filteredData, this.config);
		this.rows = page && config.paging ? this.changePage(page, sortedData) : sortedData;
		//this.length = sortedData.length;
	}#*/

	/*#public onCellClick(data: any): any {
		console.log(data);
	}#*/

	/**
	 * Método responsável por armazenar o id do #MODULO# a 
	 * removido.
	 *
	 * @param number id
	 */
	/*#excluir(id: number) {
 		this.idExcluir = id;
 	}#*/

 	/**
	 * Método responsável por remover um #MODULO#.
	 */
 	/*#onExcluir() {
 		this.#MODULO#Service.excluir(this.idExcluir);
 		location.reload();
 	}#*/

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	/*#paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.#MODULO#Service.totalRegistros();
		this.#MODULO_PLURAL# = this.#MODULO#Service.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}#*/