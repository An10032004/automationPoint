import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TestService {
  private http = inject(HttpClient);

  private appUrl = 'http://localhost:8080/user/test'

  testApi() {
    return this.http.get(this.appUrl, {
      responseType: 'text'
    })
  }
}
