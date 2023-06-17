import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-app-copyright',
  //templateUrl: './app-copyright.component.html',
  template: `  <div>
  <div style="height: 3rem;"></div>
</div>
<footer>    
  <div class="text-center p-0" >
      <p>
          © 2022 Copyright:
          <!--<a class="text-light text-center" href="https://harmonengineering.com/">Brian Harmon - Harmon Engineering L.L.C.</a>-->
          <a class=" text-center" href="https://harmonengineering.com/">Brian Harmon - Harmon Engineering L.L.C.</a>
      </p>
  </div>
</footer>`,
  styleUrls: ['./app-copyright.component.css']
})
export class AppCopyrightComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
