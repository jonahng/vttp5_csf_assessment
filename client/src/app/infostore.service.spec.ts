import { TestBed } from '@angular/core/testing';

import { InfostoreService } from './infostore.service';

describe('InfostoreService', () => {
  let service: InfostoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InfostoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
