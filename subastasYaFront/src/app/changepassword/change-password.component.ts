import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChangePasswordDTO } from '../models/change-password-dto';
import { EmailPasswordService } from '../service/email-password.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./send-email.component.css']
})
export class ChangePasswordComponent implements OnInit {
  password: string;
  confirmPassword: string;
  tokenPassword: string;
  dto: ChangePasswordDTO;

  constructor(
    private emailPasswordService: EmailPasswordService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }

  onChangePassword(): void {
    if (this.password !== this.confirmPassword) {
      Swal.fire(
        'Error',
        'Las contraseñas no coinciden',
        'error'
      );
      return;
    }
    this.tokenPassword = this.activatedRoute.snapshot.params.tokenPassword;
    this.dto = new ChangePasswordDTO(this.password, this.confirmPassword, this.tokenPassword);
    this.emailPasswordService.changePassword(this.dto).subscribe(
      data => {
        Swal.fire(
          'Información',
          data.mensaje,
          'info'
        );
        this.router.navigate(['/login']);
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
