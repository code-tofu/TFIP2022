import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Content } from 'src/models/content.model';

const uploadAPIURL: string = 'api/';
// const uploadAPIURL: string = 'http://localhost:8080/upload';

@Injectable({
  providedIn: 'root',
})
export class UploadService {
  http = inject(HttpClient);

  upload(content: Content, archive: File): Observable<any> {
    const formData = new FormData();
    formData.set('content', JSON.stringify(content));
    formData.set('archive', archive);
    console.info('uploading:', content, archive);
    return this.http.post<any>(uploadAPIURL + 'upload', formData);
  }
}
