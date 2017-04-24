/**
 * Arquivo de teste do componente LeagueVisualizarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { LeagueVisualizarComponent } from './';
import { LeagueService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('LeagueVisualizar', () => {

  let fixture: ComponentFixture<LeagueVisualizarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		LeagueVisualizarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  LeagueService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  }
    	]
    });

    fixture = TestBed.createComponent(LeagueVisualizarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
