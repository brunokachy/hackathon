import { Service } from './../service/service';
import { Component, OnInit, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/User';
import { PortalUserRole } from '../model/PortalUserRole';
import { AlertService } from '../service/alert.service';
import { JsonpModule, Jsonp } from '@angular/http';

@Component({
    moduleId: module.id,
    selector: 'home',
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {
    user = new User();
    role = new PortalUserRole();
    returnUrl: string;
    ipaddress: string;


    constructor(
        private route: ActivatedRoute,
        private alertService: AlertService,
        private router: Router,
        private elementRef: ElementRef,
        private service: Service
    ) { }


    ngOnInit(): void {
        // reset login status
        this.service.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '';
        this.newUser();
        sessionStorage.setItem('currentURL', JSON.stringify(this.returnUrl.substring(1)));

        var s1 = document.createElement("script");
        s1.src = 'app/assets/plugins/parsley/dist/parsley.js';
        this.elementRef.nativeElement.appendChild(s1);
    }



    // loginUser() {
    //     this.service.login(this.user.emailAddress, this.user.password).subscribe(
    //         data => {
    //             this.handleUserRole(data);
    //         },
    //         error => {
    //             console.log(error);
    //             this.alertService.error("Invalid Password.");
    //         });
    // }

    // handleUserRole(loggedInUser: User) {
    //     this.readService.getUserRole(loggedInUser.id).subscribe(
    //         data => {
    //             if (data != null) {
    //                 if (data.designation == 'SUPERADMIN') {
    //                     sessionStorage.setItem('role', JSON.stringify(data));
    //                     sessionStorage.setItem('currentUser', JSON.stringify(loggedInUser));
    //                     // this.logUserSession(loggedInUser.emailAddress, loggedInUser.id);
    //                     this.router.navigate([this.returnUrl]);
    //                 } else if (data.designation == 'BUSINESSMANAGER') {
    //                     if (data.business.approved == true && data.business.active == true) {
    //                         sessionStorage.setItem('role', JSON.stringify(data));
    //                         sessionStorage.setItem('business', JSON.stringify(data.business));
    //                         sessionStorage.setItem('currentUser', JSON.stringify(loggedInUser));
    //                         // this.logUserSession(loggedInUser.emailAddress, loggedInUser.id);
    //                         this.genericService.continiousUserValidityCheck();
    //                         this.router.navigate([this.returnUrl]);
    //                     } else {
    //                         this.alertService.error("Your Business is in-active, please contact admin.");
    //                     }
    //                 } else if (data.designation == "ATTENDANT") {
    //                     if (data.station != null) {
    //                         if (data.station.active) {
    //                             if (data.dispensingPointAttendant != null) {
    //                                 sessionStorage.setItem('role', JSON.stringify(data));
    //                                 sessionStorage.setItem('business', JSON.stringify(data.business));
    //                                 sessionStorage.setItem('station', JSON.stringify(data.station));
    //                                 sessionStorage.setItem('currentUser', JSON.stringify(loggedInUser));
    //                                 sessionStorage.setItem('dispensingPointAttendant', JSON.stringify(data.dispensingPointAttendant));
    //                                 sessionStorage.setItem('dispensingPoint', JSON.stringify(data.dispensingPointAttendant.dispensingPoint));
    //                                 this.genericService.continiousUserValidityCheck();
    //                                 this.router.navigate([this.returnUrl]);
    //                             } else {
    //                                 this.alertService.error("You don't have a dispensing point assigned to you, please contact admin.");
    //                             }
    //                             this.alertService.error("You don't have a dispensing point assigned to you, please contact admin.");
    //                         }
    //                     } else {
    //                         this.alertService.error("You don't have a dispensing point assigned to you, please contact admin.");
    //                     }
    //                 } else if (data.designation == "STATIONMANAGER") {
    //                     if (data.station != null) {
    //                         if (data.station.active) {
    //                             sessionStorage.setItem('role', JSON.stringify(data));
    //                             sessionStorage.setItem('business', JSON.stringify(data.business));
    //                             sessionStorage.setItem('station', JSON.stringify(data.station));
    //                             sessionStorage.setItem('currentUser', JSON.stringify(loggedInUser));
    //                             // this.logUserSession(loggedInUser.emailAddress, loggedInUser.id);
    //                             this.genericService.continiousUserValidityCheck();
    //                             this.router.navigate([this.returnUrl]);
    //                         } else {
    //                             this.alertService.error("You don't have a station assigned to you, please contact admin.");
    //                         }
    //                     } else {
    //                         this.alertService.error("You don't have a station assigned to you, please contact admin.");
    //                     }
    //                 }
    //             } else {
    //                 this.alertService.error("You don't have a role assigned to you, please contact admin.");
    //             }
    //         }, error => {
    //             console.log(error);
    //             this.alertService.error("Unable to Login, please contact admin.");
    //         });
    // }

    // validateLoginEmail() {
    //     this.genericService.validateLoginEmail(this.user.emailAddress).subscribe(
    //         data => {
    //             if (!data.active) {
    //                 this.alertService.error("User is disabled. Please contact admin.");
    //             } else {
    //                 this.loginUser();
    //             }

    //         }, error => {
    //             console.log(error);
    //             this.alertService.error("Email address does not exist");
    //         });
    // }

    newUser() {
        this.user = new User();
    }

    startChangePassword() {
        sessionStorage.setItem('changePassword', JSON.stringify(true));
    }
}
