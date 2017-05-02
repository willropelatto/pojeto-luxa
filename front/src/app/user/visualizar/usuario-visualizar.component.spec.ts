/**
 * Arquivo de teste do componente UserVisualizarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { UserVisualizarComponent } from './';
import { UserService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('UserVisualizar', () => {

  let fixture: ComponentFixture<UserVisualizarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		UserVisualizarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  UserService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  }
    	]
    });

    fixture = TestBed.createComponent(UserVisualizarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
