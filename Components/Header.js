import React, { Component } from 'react'
import {HeaderItems} from "./HeaderItems"
import CloudLogo from "../cloud-ensemble-logo.png"
import './Header.css'
import { Button } from './Button'


class Header extends Component{
render(){
return(
<nav className="HeaderItems">
    <div className = "header-logo"> <img src={CloudLogo} alt="Cloud Ensemble Logo"/>
    <div className = "menu-icon" onClick={this.handleClick}>
    </div>
    </div>
    <ul className = 'header-menu'>
        {HeaderItems.map((item,index) => {
            return(
                <li>
                    <a className = {item.cName} href={item.url}> 
                    {item.title}
                    </a>
                </li>

            )

        })}
        
    </ul>
    <Button> CREATE</Button>
</nav>
)
}
}

export default Header