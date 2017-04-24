/**
 * Arquivo de teste do componente TeamListarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { TeamListarComponent } from './';
import { TeamService } from '../shared';
import { 
	ModalUtilComponent, 
	KzPaginacaoComponent,
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('TeamListar', () => {

  let fixture: ComponentFixture<TeamListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		TeamListarComponent,
    		ModalUtilComponent,
    		KzPaginacaoComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  TeamService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  }
    	]
    });

    fixture = TestBed.createComponent(TeamListarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
