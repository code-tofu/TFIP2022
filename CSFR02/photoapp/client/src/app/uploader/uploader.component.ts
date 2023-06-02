import { Component, ElementRef, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { Content } from 'src/models/content.model';
import { UploadService } from 'src/services/upload.service';

@Component({
  selector: 'app-uploader',
  templateUrl: './uploader.component.html',
  styleUrls: ['./uploader.component.css'],
})
export class UploaderComponent {
  @ViewChild('uploadElement')
  uploadElement!: ElementRef;

  archiveForm!: FormGroup;
  fb = inject(FormBuilder);
  uploadSvc = inject(UploadService);

  ngOnInit(): void {
    this.archiveForm = this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>(''),
      file: this.fb.control<File | null>(null, [Validators.required]),
    });
  }

  upload() {
    const f: File = this.uploadElement.nativeElement.files[0];
    // let content: Content = { ...this.archiveForm.value };
    let content: Content = {
      name: this.archiveForm.value.name,
      title: this.archiveForm.value.title,
      comments: !this.archiveForm.value.comments
        ? ''
        : this.archiveForm.value.comments,
    };
    console.info(content);
    firstValueFrom(this.uploadSvc.upload(content, f))
      .then((resp) => {
        console.log(resp);
        alert('Upload Successful');
        this.archiveForm.reset();
      })
      .catch((err) => {
        console.log(err);
        alert('Upload Failed');
      });
  }
}
