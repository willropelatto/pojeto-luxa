/**
 * Arquivo de teste do componente TeamCadastrarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { TeamCadastrarComponent } from './';
import { TeamService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub,
	RouterStubService
} from '../../';

describe('TeamCadastrar', () => {

  let fixture: ComponentFixture<TeamCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	imports: [
    		FormsModule
    	],
    	declarations: [ 
    		TeamCadastrarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  TeamService,
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

    fixture = TestBed.createComponent(TeamCadastrarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
