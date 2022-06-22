import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OfferDTO } from '../models/offer-dto';

@Injectable({
  providedIn: 'root'
})
export class OfertaService {

  private urlEndpoint: string = 'http://localhost:8080/offer';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) { }

  public newOffer(offerDto: OfferDTO): Observable<OfferDTO> {
    console.log(offerDto.announcementId);
    console.log(offerDto.bidderUserName);
    console.log(offerDto.offerValue);
    return this.httpClient.post<OfferDTO>(`${this.urlEndpoint}/new`, offerDto, {headers: this.httpHeaders});
  }
}
