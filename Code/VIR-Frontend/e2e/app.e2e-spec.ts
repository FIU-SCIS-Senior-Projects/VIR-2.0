import { VIRFrontEndPage } from './app.po';

describe('vir-front-end App', () => {
  let page: VIRFrontEndPage;

  beforeEach(() => {
    page = new VIRFrontEndPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
