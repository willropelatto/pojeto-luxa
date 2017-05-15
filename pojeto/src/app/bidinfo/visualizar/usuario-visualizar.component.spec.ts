/**
 * Arquivo de teste do componente BidinfoVisualizarComponent.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { BidinfoVisualizarComponent } from './';
import { BidinfoService } from '../';
import { 
	RouterLinkStubDirective,
	ActivatedRouteStub
} from '../../';

describe('BidinfoVisualizar', () => {

  let fixture: ComponentFixture<BidinfoVisualizarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
    	declarations: [ 
    		BidinfoVisualizarComponent,
    		RouterLinkStubDirective
    	],
    	providers:    [
    	  BidinfoService,
    	  { 
    	  	provide: ActivatedRoute, 
    	  	useValue: new ActivatedRouteStub() 
    	  }
    	]
    });

    fixture = TestBed.createComponent(BidinfoVisualizarComponent);
  });

  it('deve garantir que o componente tenha sido criado', () => {
    expect(fixture).toBeDefined();
  });
  
});
