import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class HttpUtilService {

	private API_URL: string = 'http://nbbnu008771:3030/ApiVcEhSafado/rest/';
	

	//http://localhost:3030/ApiVcEhSafado/rest/player/getAllPlayers 

	url(path: string) {
		return this.API_URL + path;
	}

	headers() {
		let headersParams = { 'Content-Type': 'application/json' };
	//	if (localStorage['token']) {
	//		headersParams['Authorization'] = localStorage['token'];
	//	}
		
		let headers = new Headers(headersParams);
		headers.append('Access-Control-Allow-Origin','*');
		headers.append("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    	let options = new RequestOptions({ headers: headers });
    	return options;
	}

	extrairDados(response: Response) {
    	let data = response.json();
    	return data || {};
  	}

  	processarErros(erro: any) {
	    return Observable.throw('Erro acessando servidor remoto.');
	}
}