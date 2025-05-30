import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { IpCard } from './components/ip-card/ip-card';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, IpCard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'getmyip';
}
