/**
 * Arquivo de teste do componente BidinfoEditarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { BidinfoEditarComponent } from './';
import { BidinfoService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub,
	RouterStubService
} from '../../';

describe('BidinfoEditar', () => {

  let fixture: ComponentFixture<BidinfoEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	imports: [
    		FormsModule
    	],
    	declarations: [ 
    		BidinfoEditarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  BidinfoService,
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

    fixture = TestBed.createComponent(BidinfoEditarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
