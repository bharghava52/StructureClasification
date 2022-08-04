import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Injectable()
export class mainConfiguration {
    public Server = window.location.href;

    public ServerWithCompareDocs = this.Server + "compareDocs";

}