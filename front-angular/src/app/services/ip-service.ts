import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { of } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IpService {

  constructor(private http: HttpClient) { }

  getHeroes(): Observable<String> {
    return this.http.get<String>(`${environment.backendApiUrl}/ip`)
  }
  getIp() {
    //return this.http.get();
    return of({ ip: '192.168.0.1' });
  }
}
