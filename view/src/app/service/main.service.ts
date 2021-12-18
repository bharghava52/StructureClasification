import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { mainConfiguration } from "./main.constants";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class MainService {
    constructor(protected http: HttpClient, private configuration: mainConfiguration) { }

    public submitFileDetails(files: any): Observable<any> {
        const headers: HttpHeaders = new HttpHeaders();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.configuration.ServerWithCompareDocs, files);
    }
}