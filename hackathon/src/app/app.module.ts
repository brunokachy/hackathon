import { LoginComponent } from './login/login.component';
import { AlertComponent } from './alert.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { ImageUploadModule } from 'angular2-image-upload';
import { GooglePlaceModule } from 'ng2-google-place-autocomplete';
import { DataTableModule } from "angular2-datatable";

import { AuthGuard } from './guards/auth.guard';
import { Service } from './service/service';
import { AlertService } from './service/alert.service';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    GooglePlaceModule,
    ImageUploadModule.forRoot(),
    DataTableModule,
    JsonpModule
  ],
  providers: [
    AlertService,
    Service,
    AuthGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
