/**
 * Arquivo de teste do componente PlayerVisualizarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { PlayerVisualizarComponent } from './';
import { PlayerService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('PlayerVisualizar', () => {

  let fixture: ComponentFixture<PlayerVisualizarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		PlayerVisualizarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  PlayerService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  }
    	]
    });

    fixture = TestBed.createComponent(PlayerVisualizarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
