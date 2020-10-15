
import React from "react";
import PropTypes from "prop-types";

import "./logInScreen.css";

class LogInScreen extends React.Component {

  constructor(props) {
      super(props);
      this.state = {

      };
  }
  
    handleKeyPress(e) {
      if (e.key === 'Enter' && this.props.onEnter) {
        let name;
        let id;
        let index = -1;
        const value = e.target.value;
        if (e.target.name.search("::") > -1) {
            const varCount = e.target.name.split("::").length;
            if (varCount === 2) {
                name = e.target.name.split("::")[0];
                id = e.target.name.split("::")[1];
            } else if (varCount === 3) {
                name = e.target.name.split("::")[0];
                index = e.target.name.split("::")[1];
                id = e.target.name.split("::")[2];
            }
        } else {
            name = e.target.name;
        }
        this.props.onEnter(
          {
            type: "textinput",
            name: name,
            value: value,
            index: index,
            id: id
          }
        );
      }
    }
    
  handleClick(target) {
    if (this.props.onClick) {
        let name;
        let id;
        let index = -1;
        if (target.search("::") > -1) {
            const varCount = target.split("::").length;
            if (varCount === 2) {
                name = target.split("::")[0];
                id = target.split("::")[1];
            } else if (varCount === 3) {
                name = target.split("::")[0];
                index = parseInt(target.split("::")[1]);
                id = target.split("::")[2];
            }
        } else {
            name = target;
        }
        this.props.onClick({ type: 'button', name: name, index: index, id: id });
    }
  }
    
    handleChangeTextinput(e) {
      let name;
      let id;
      let index = -1;
      const value = e.target.value;
      if (e.target.name.search('::') > -1) {
          const varCount = e.target.name.split("::").length;
          if (varCount === 2) {
              name = e.target.name.split("::")[0];
              id = e.target.name.split("::")[1];
          } else if (varCount === 3) {
              name = e.target.name.split("::")[0];
              index = e.target.name.split("::")[1];
              id = e.target.name.split("::")[2];
          }
      } else {
          name = e.target.name;
      }
      let state = this.state;
      state[e.target.name.split('::').join('')] = value;
      this.setState(state, () => {
          if (this.props.onChange) {
              this.props.onChange({ type: 'textinput', name: name, value: value, index: index, id: id });
          }
      });
    }
    
      handleChangeCheckbox(e) {
        let name;
        let id;
        let index = -1;
        const value = e.target.checked;
        if (e.target.name.search('::') > -1) {
            const varCount = e.target.name.split("::").length;
            if (varCount === 2) {
                name = e.target.name.split("::")[0];
                id = e.target.name.split("::")[1];
            } else if (varCount === 3) {
                name = e.target.name.split("::")[0];
                index = e.target.name.split("::")[1];
                id = e.target.name.split("::")[2];
            }
        } else {
            name = e.target.name;
        }
        let state = this.state;
        state[e.target.name.split('::').join('')] = value;
        this.setState(state, () => {
            if (this.props.onChange) {
                this.props.onChange({ type: 'checkbox', name: name, value: value, index : index, id: id });
            }
        });
    }
    
  render() {
    
    return (
          <div data-layer="25efb7b6-acad-4486-8d3b-d6a226e5c83e" className="logInScreen">        <div data-layer="85cbfb2a-90cf-4608-b67d-6e1345e292ba" className="nserl13661"></div>
        <div data-layer="761bd75c-f22e-4c3e-9400-e9e007832aa2" className="login">            <svg data-layer="1859af10-8043-4c56-977a-fb723ad33198" preserveAspectRatio="none" viewBox="-0.08806052058935165 -0.07958096265792847 980.7978515625 1080" className="rightSide"><path d="M 0.0006927682552486658 0.0006106167566031218 L 980.7097778320313 -0.07958096265792847 L 980.6210327148438 1079.840087890625 L -0.08806052058935165 1079.92041015625 L 0.0006927682552486658 0.0006106167566031218 Z"  /></svg>
            <div data-layer="1330fd5a-ec4d-47a6-9ce8-e6c3740d552c" className="loginWelcomeBackScreen">                <div data-layer="4356fe86-b375-4ccf-9c0c-60a2df3152b2" className="welcomeBack">Welcome back!</div>
                <div data-layer="029230d7-ec61-4a5f-a801-ab28cdbc0b8d" className="logIn">Log In</div>
</div>
            <div data-layer="abecb13f-9466-4b9d-8307-429a89615f6c" className="password">                <div data-layer="206c5c5b-235b-494b-8734-0434d20387ef" className="passwordf34cb268">Password</div>
                <input data-layer="d48d6f9d-e1fa-482d-a62d-2fc5ffaff19a" className="passwordForm" type="password" id="passwordForm-10178db51e62" name='passwordForm' placeholder='Password'  onChange={this.handleChangeTextinput.bind(this)} onKeyPress={this.handleKeyPress.bind(this)} />
</div>
            <div data-layer="5e267a9d-1403-42cd-a7c2-d854f66127d4" className="email">                <div data-layer="0018a27b-0808-494e-ab36-c19ce9bc0973" className="emailLabel">Email</div>
                <input data-layer="4eabadb6-876b-47eb-bc9f-13fdee9f9180" className="usernameForm" type="text" id="usernameForm-36741e1cdd9a" name='usernameForm' placeholder='Please enter your email.'  onChange={this.handleChangeTextinput.bind(this)} onKeyPress={this.handleKeyPress.bind(this)} />
</div>
            <div data-layer="995a5873-23b6-4229-a0ed-be576754be3d" className="defaultButton">                <button type="button" data-layer="ee8047b0-a327-4e14-85f7-5bcb67b1675f" name="'buttonLogin'" className="buttonLogin" onClick={this.handleClick.bind(this, 'buttonLogin')}>                    <div data-layer="ee0532e8-5bea-45d0-ba3c-20df617fa27e" className="activeState">                        <div data-layer="877ac06b-f063-4ab1-9303-4066092fb504" className="rectangle1515"></div>
                        <div data-layer="3188fc55-5928-4389-9f12-78fd59959b31" className="signIn">SIGN IN</div>
</div>
                    <div data-layer="ad1c569d-effc-481d-9351-ca6083ddb9d5" className="hoverState">                        <div data-layer="a5c8bace-6f2b-423f-8cff-c1f3273d765c" className="rectangle1515ac505faa"></div>
                        <div data-layer="99097387-b844-4521-96b8-bbca08ce8605" className="signIn177c060f">SIGN IN</div>
</div>
                    <div data-layer="9287e468-c63f-450c-9a85-f541f424a369" className="buttonLogin2ead04da">                        <div data-layer="d63a4817-0563-4e17-9571-3de6cc0463b9" className="rectangle1515f416294e"></div>
                        <div data-layer="38448f88-feb3-4c94-807b-1735fd891f3b" className="signIn3d82c550">SIGN IN</div>
</div>
</button>
</div>
            <div data-layer="8452b4af-c6e4-4a72-9f63-4b4958da16e1" className="staySignedInBox">                <div data-layer="5b4fc735-c159-4d1d-8405-b332a2e258c9" className="rememberMe"><input data-layer="5b4fc735-c159-4d1d-8405-b332a2e258c9" type="checkbox" id='rememberMe-a842295c917f' name='rememberMe'  onChange={this.handleChangeCheckbox.bind(this)} /><label className="input-label" htmlFor='rememberMe-a842295c917f'></label>                    <div data-layer="40b0fe48-fd16-49a7-a85b-1eb74b3343f7" className="checkedState">                        <div data-layer="a1aeb1af-57c2-48e2-a2c7-ed796dee09d3" className="container"></div>
                        <div data-layer="155f39ac-7b2b-402d-8c36-4ed136644858" className="staySignedIn">Stay Signed In</div>
                        <svg data-layer="f20c9224-5efa-4d1a-b115-f9dcb5cd6301" preserveAspectRatio="none" viewBox="0.00001800060272216797 4.577220916748047 14.7509765625 11" className="iconAwesomeCheck"><path d="M 5.010077476501465 15.36110973358154 L 0.2160310000181198 10.56706428527832 C -0.07198632508516312 10.2790470123291 -0.07198632508516312 9.81205940246582 0.2160310000181198 9.524013519287109 L 1.259052991867065 8.480962753295898 C 1.547070264816284 8.192916870117188 2.014086723327637 8.192916870117188 2.302103996276855 8.480962753295898 L 5.53160285949707 11.7104320526123 L 12.44882965087891 4.793233871459961 C 12.73684692382813 4.505216598510742 13.2038631439209 4.505216598510742 13.49188041687012 4.793233871459961 L 14.53490352630615 5.836284637451172 C 14.82292079925537 6.124301910400391 14.82292079925537 6.591289520263672 14.53490352630615 6.879335403442383 L 6.053128242492676 15.36113929748535 C 5.765081882476807 15.64915561676025 5.298094272613525 15.64915561676025 5.010077476501465 15.36110973358154 Z"  /></svg>
</div>
                    <div data-layer="3f49379b-a40a-41b9-b105-4df02afe417c" className="defaultState">                        <div data-layer="c02581ea-5ada-4b3e-923d-7ece2ada92e7" className="container6a5b99b7"></div>
                        <div data-layer="420e1fa0-e952-4815-ad1a-7737dffab521" className="staySignedIn6949c7cd">Stay Signed In</div>
</div>
                    <div data-layer="dfb45841-7ef6-4e10-bb09-5992d7c7c61f" className="defaultState442a8427">                        <div data-layer="8846c05e-27f0-454d-93f7-ff777764e1f6" className="containerfdeffd7f"></div>
                        <div data-layer="bf9786bb-3957-4eb6-ba0b-f3189bbb3130" className="staySignedIn04635b48">Stay Signed In</div>
</div>
</div>
</div>
            <div data-layer="8b80938c-35bd-47f3-82d5-d93f9dd2b158" className="forgotYourPassword">Forgot your password?</div>
</div>
</div>

    );
  }
}

LogInScreen.propTypes = {

}

LogInScreen.defaultProps = {

}


export default LogInScreen;
          