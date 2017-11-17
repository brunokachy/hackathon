import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';

import { User } from '../model/User';
import { PortalUserRole } from '../model/PortalUserRole';

@Injectable()
export class Service {
    // URL to web api
    private baseURL = 'http://localhost:8080/Gapso-web/service/';
   
    
   
    constructor(public http: Http, private router: Router, ) { }


    
   

    logout() {
        sessionStorage.clear();
    }

    private jwt() {
        // create authorization header with jwt token
        let headers = new Headers({ 'Content-Type': 'application/json' });
        return new RequestOptions({ headers: headers });

    }

}
