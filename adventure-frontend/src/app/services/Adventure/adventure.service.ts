import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdventureService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  adventureSubject = new BehaviorSubject<any>({
    adventures: [],
    loading: false,
    newAdventure: null,
  });

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getAdventures(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(`${this.baseUrl}/api/adventures`, { headers }).pipe(
      tap((adventures) => {
        const currentState = this.adventureSubject.value;
        this.adventureSubject.next({
          ...currentState,
          adventures: adventures,
        });
      })
    );
  }

  createAdventure(adventure: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http
      .post(`${this.baseUrl}/api/adventures`, adventure, { headers })
      .pipe(
        tap((newAdventure) => {
          const currentState = this.adventureSubject.value;
          this.adventureSubject.next({
            ...currentState,
            adventures: [newAdventure, ...currentState.adventures],
          });
        })
      );
  }

  updateAdventure(adventure: any): Observable<any> {
    const headers = this.getHeaders();

    return this.http
      .put(`${this.baseUrl}/api/adventures/${adventure.id}`, adventure, {
        headers,
      })
      .pipe(
        tap((updatedAdventure: any) => {
          const currentState = this.adventureSubject.value;
          const updatedAdventures = currentState.adventures.map(
            (adventure: any) =>
              adventure.id === updatedAdventure.id
                ? updatedAdventure
                : adventure
          );
          this.adventureSubject.next({
            ...currentState,
            adventures: updatedAdventures,
          });
        })
      );
  }

  likeAdventure(adventureId: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http
      .put(`${this.baseUrl}/api/adventures/${adventureId}/like`, adventureId, {
        headers,
      })
      .pipe(
        tap((updatedAdventure: any) => {
          const currentState = this.adventureSubject.value;
          const updatedAdventures = currentState.adventures.map(
            (adventure: any) =>
              adventure.id === updatedAdventure.id
                ? updatedAdventure
                : adventure
          );
          this.adventureSubject.next({
            ...currentState,
            adventures: updatedAdventures,
          });
        })
      );
  }

  deleteAdventure(adventureId: any): Observable<any> {
    const headers = this.getHeaders();

    return this.http
      .delete(`${this.baseUrl}/api/adventures/${adventureId}`, { headers })
      .pipe(
        tap((deletedAdventure: any) => {
          const currentState = this.adventureSubject.value;
          const updatedAdventures = currentState.adventures.filter(
            (adventure: any) => adventure.id !== adventureId
          );
          this.adventureSubject.next({
            ...currentState,
            adventures: updatedAdventures,
          });
        })
      );
  }
}
