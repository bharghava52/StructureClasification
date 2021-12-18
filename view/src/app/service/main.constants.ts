import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Injectable()
export class mainConfiguration {
    public Server = 'http://localhost:9099/';

    public ServerWithCompareDocs = this.Server + "compareDocs";

}