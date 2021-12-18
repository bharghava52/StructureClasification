import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MainService } from './service/main.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Structure Clasification Application';
  documentForm: any;
  filesList: any = [];
  documentResult: any = {
    "fileData": {}
  };
  constructor(private fb: FormBuilder, private mainService: MainService) {
    this.buildForm();
  }

  buildForm() {
    this.addNextFile();
    this.addNextFile();
  }


  addNextFile() {
    if (this.filesList.length < 5) {
      this.filesList.push({ fileData: "" });
    }
  }

  removeFile(i: number) {
    this.filesList = this.filesList.filter((v: any, index: number) => i != index);
  }

  done() {
    let arrayList: any[] = [];
    this.filesList.forEach((fileElement: any) => {
      if (fileElement.fileData != "") {
        arrayList.push(fileElement.fileData);
      }
    });
    console.log(arrayList);
    let data = {
      fileData: arrayList
    }
    this.mainService.submitFileDetails(data).subscribe(res => {
      this.documentResult = res;
    }, err => {
      console.log(err);
    });
  }
}
