import { Component, OnInit } from '@angular/core';
import { EmailPasswordService } from '../service/email-password.service';

import Swal from 'sweetalert2';
import { EmailValuesDTO } from '../models/email-values-dto';
import { EmailValidator } from '@angular/forms';

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.css']
})
export class SendEmailComponent implements OnInit {
  mailTo: string;
  dto: EmailValuesDTO;

  constructor(
    private emailPasswordService: EmailPasswordService
  ) { }

  ngOnInit() {
  }

  onSendEmail(): void {
    this.dto = new EmailValuesDTO(this.mailTo);
    this.emailPasswordService.sendEmail(this.dto).subscribe(
      data => {
        Swal.fire(
          'Información',
          data.mensaje,
          'info'
        );
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
      }
    );
  }

}
