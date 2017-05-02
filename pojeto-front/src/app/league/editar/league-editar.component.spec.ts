/**
 * Arquivo de teste do componente LeagueEditarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { LeagueEditarComponent } from './';
import { LeagueService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub,
	RouterStubService
} from '../../';

describe('LeagueEditar', () => {

  let fixture: ComponentFixture<LeagueEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	imports: [
    		FormsModule
    	],
    	declarations: [ 
    		LeagueEditarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  LeagueService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  },
    	  {
    	  	provide: Router,
    	  	useValue: new RouterStubService()
    	  }
    	]
    });

    fixture = TestBed.createComponent(LeagueEditarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
