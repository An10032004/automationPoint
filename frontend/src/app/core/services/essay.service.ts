import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Essay } from '../models/essay.model';

@Injectable({
    providedIn: 'root',
})

export class EssayService {
    private http = inject(HttpClient)
    private appUrl = "http://localhost:8080/essay"

    getAllEssay() {
        return this.http.get<Essay[]>(`${this.appUrl}`)
    }

    getEssayById(id: number) {
        return this.http.get<Essay>(`${this.appUrl}/${id}`)
    }

    createEssay(data: Essay) {
        return this.http.post<Essay>(`${this.appUrl}/create`, data, {
            responseType: 'text' as 'json'
        })
    }

    updateEssay(data: Essay) {
        return this.http.put<Essay>(`${this.appUrl}/update/${data.id}`, data, {
            responseType: 'text' as 'json'
        })
    }

    deleteEssay(id: number) {
        return this.http.delete<Essay>(`${this.appUrl}/delete/${id}`, {
            responseType: 'text' as 'json'
        })
    }
    essayScore(id: number) {
        return this.http.post<Essay>(`${this.appUrl}/score/${id}`, null)
    }
}