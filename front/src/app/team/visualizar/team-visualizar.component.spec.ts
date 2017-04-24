/**
 * Arquivo de teste do componente TeamVisualizarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { TeamVisualizarComponent } from './';
import { TeamService } from '../shared';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('TeamVisualizar', () => {

  let fixture: ComponentFixture<TeamVisualizarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		TeamVisualizarComponent,
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

    fixture = TestBed.createComponent(TeamVisualizarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
