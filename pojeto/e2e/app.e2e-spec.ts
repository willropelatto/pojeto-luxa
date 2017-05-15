import { PojetoPage } from './app.po';

describe('pojeto App', () => {
  let page: PojetoPage;

  beforeEach(() => {
    page = new PojetoPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
