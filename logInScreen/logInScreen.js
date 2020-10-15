import React, {Component} from 'react';
import PropTypes from "prop-types";
import {StyleSheet, Text, View, TextInput, FlatList, Picker, ScrollView, TouchableHighlight} from 'react-native';
import {Image as ReactImage} from 'react-native';
import Svg, {Defs, Pattern} from 'react-native-svg';
import {Path as SvgPath} from 'react-native-svg';
import {Text as SvgText} from 'react-native-svg';
import {Image as SvgImage} from 'react-native-svg';

export default class LogInScreen extends Component {

  constructor(props) {
      super(props);
      this.state = {
          
      };
  }


  handlePress(target, owner) {
    if (this.props.onPress) {
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
        this.props.onPress({ type: 'button', name: name, index: index, id: id, owner: owner });
    }
  }

  handleChangeTextinput(name, value) {
      let id;
      let index = -1;
      if (name.search('::') > -1) {
          const varCount = name.split("::").length;
          if (varCount === 2) {
              name = name.split("::")[0];
              id = name.split("::")[1];
          } else if (varCount === 3) {
              name = name.split("::")[0];
              index = name.split("::")[1];
              id = name.split("::")[2];
          }
      } else {
          name = name;
      }
      let state = this.state;
      state[name.split('::').join('')] = value;
      this.setState(state, () => {
          if (this.props.onChange) {
              this.props.onChange({ type: 'textinput', name: name, value: value, index: index, id: id });
          }
      });
  }

  render() {
    
    return (
    <ScrollView data-layer="25efb7b6-acad-4486-8d3b-d6a226e5c83e" style={styles.logInScreen}>
        <ReactImage data-layer="85cbfb2a-90cf-4608-b67d-6e1345e292ba" source={require('./assets/nserl13661.png')} style={styles.logInScreen_nserl13661} />
        <View data-layer="761bd75c-f22e-4c3e-9400-e9e007832aa2" style={styles.logInScreen_login}>
            <Svg data-layer="1859af10-8043-4c56-977a-fb723ad33198" style={styles.logInScreen_login_rightSide} preserveAspectRatio="none" viewBox="-0.08806052058935165 -0.07958096265792847 980.7978515625 1080" fill="rgba(255, 255, 255, 1)"><SvgPath d="M 0.0006927682552486658 0.0006106167566031218 L 980.7097778320313 -0.07958096265792847 L 980.6210327148438 1079.840087890625 L -0.08806052058935165 1079.92041015625 L 0.0006927682552486658 0.0006106167566031218 Z"  /></Svg>
            <View data-layer="1330fd5a-ec4d-47a6-9ce8-e6c3740d552c" style={styles.logInScreen_login_loginWelcomeBackScreen}>
                <Text data-layer="4356fe86-b375-4ccf-9c0c-60a2df3152b2" style={styles.logInScreen_login_loginWelcomeBackScreen_welcomeBack}>Welcome back!</Text>
                <View style={styles.logInScreen_login_loginWelcomeBackScreen_logIn}><Text data-layer="029230d7-ec61-4a5f-a801-ab28cdbc0b8d" style={{"marginTop":-7,"color":"rgba(0, 0, 0, 1)","fontSize":41,"fontWeight":"700","fontStyle":"normal","fontFamily":"Segoe UI","textAlign":"left","lineHeight":45.1}}>Log In</Text></View>
            </View>
            <View data-layer="abecb13f-9466-4b9d-8307-429a89615f6c" style={styles.logInScreen_login_password}>
                <Text data-layer="206c5c5b-235b-494b-8734-0434d20387ef" style={styles.logInScreen_login_password_passwordf34cb268}>Password</Text>
                <TextInput data-layer="d48d6f9d-e1fa-482d-a62d-2fc5ffaff19a" style={styles.logInScreen_login_password_passwordForm} type="password" id="passwordForm-87d9f86b0cc8" name='passwordForm' placeholder='Password' placeholderTextColor="rgba(0, 0, 0, 1)"  onChangeText={this.handleChangeTextinput.bind(this, 'passwordForm')} />
            </View>
            <View data-layer="5e267a9d-1403-42cd-a7c2-d854f66127d4" style={styles.logInScreen_login_email}>
                <Text data-layer="0018a27b-0808-494e-ab36-c19ce9bc0973" style={styles.logInScreen_login_email_emailLabel}>Email</Text>
                <TextInput data-layer="4eabadb6-876b-47eb-bc9f-13fdee9f9180" style={styles.logInScreen_login_email_usernameForm} type="text" id="usernameForm-a3907a684369" name='usernameForm' placeholder='Please enter your email.' placeholderTextColor="rgba(0, 0, 0, 1)"  onChangeText={this.handleChangeTextinput.bind(this, 'usernameForm')} />
            </View>
            <View data-layer="995a5873-23b6-4229-a0ed-be576754be3d" style={styles.logInScreen_login_defaultButton}>
                <TouchableHighlight onPress={this.handlePress.bind(this, 'buttonLogin')}>
                    <View data-layer="ee8047b0-a327-4e14-85f7-5bcb67b1675f" style={styles.logInScreen_login_defaultButton_buttonLogin}>
                        <View data-layer="9287e468-c63f-450c-9a85-f541f424a369" style={styles.logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da}>
                            <View data-layer="d63a4817-0563-4e17-9571-3de6cc0463b9" style={styles.logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da_rectangle1515f416294e}></View>
                            <View style={styles.logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da_signIn3d82c550}><Text data-layer="38448f88-feb3-4c94-807b-1735fd891f3b" style={{"marginTop":-3.5,"color":"rgba(255, 255, 255, 1)","fontSize":21,"fontWeight":"700","fontStyle":"normal","fontFamily":"Segoe UI","textAlign":"left","lineHeight":23.1}}>SIGN IN</Text></View>
                        </View>
                    </View>
                </TouchableHighlight>
            </View>
            <View data-layer="8452b4af-c6e4-4a72-9f63-4b4958da16e1" style={styles.logInScreen_login_staySignedInBox}>

            </View>
            <Text data-layer="8b80938c-35bd-47f3-82d5-d93f9dd2b158" style={styles.logInScreen_login_forgotYourPassword}>Forgot your password?</Text>
        </View>
    </ScrollView>
    );
  }
}

LogInScreen.propTypes = {

}

LogInScreen.defaultProps = {

}


const styles = StyleSheet.create({
  "logInScreen": {
    "opacity": 1,
    "position": "relative",
    "backgroundColor": "rgba(255, 255, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_nserl13661": {
    "opacity": 1,
    "position": "absolute",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "borderTopLeftRadius": 0,
    "borderTopRightRadius": 0,
    "borderBottomLeftRadius": 0,
    "borderBottomRightRadius": 0,
    "width": 939.01,
    "height": "auto",
    "left": 0,
    "top": 0,
    "bottom": 0
  },
  "logInScreen_login": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 980.8,
    "height": 1080,
    "top": 0.01,
    "right": 0.19
  },
  "logInScreen_login_rightSide": {
    "opacity": 1,
    "position": "absolute",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_loginWelcomeBackScreen": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 161,
    "height": 102,
    "top": 113.99,
    "right": 417.81
  },
  "logInScreen_login_loginWelcomeBackScreen_welcomeBack": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 24,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 161,
    "height": 44,
    "left": 0,
    "top": 68
  },
  "logInScreen_login_loginWelcomeBackScreen_logIn": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 41,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 45.1,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 121,
    "height": 54,
    "left": 20,
    "top": 13.5
  },
  "logInScreen_login_password": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 370,
    "height": 74,
    "left": 298.99,
    "top": 391.99
  },
  "logInScreen_login_password_passwordf34cb268": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 66,
    "height": 41,
    "left": 0,
    "top": -7.5
  },
  "logInScreen_login_password_passwordForm": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 9.999755859375,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 4,
    "borderTopRightRadius": 4,
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 4,
    "textAlign": "left",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 14,
    "fontFamily": "Segoe UI",
    "width": "100.00%",
    "height": 41,
    "left": 0,
    "bottom": 0
  },
  "logInScreen_login_password_passwordForm_passwordContainer": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 4,
    "borderTopRightRadius": 4,
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 4,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_password_passwordForm_password0860ac5c": {
    "opacity": 0.5099999904632568,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": -175.5,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 351,
    "height": 41,
    "left": "50%",
    "top": 1.5
  },
  "logInScreen_login_password_passwordForm_text7f8e0daa": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 14,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": -175.5,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 351,
    "height": 43,
    "left": "50%",
    "top": 1.5
  },
  "logInScreen_login_email": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 370,
    "height": 78,
    "left": 298.99,
    "top": 273.99
  },
  "logInScreen_login_email_emailLabel": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 38,
    "height": 41,
    "left": 0,
    "top": -7.5
  },
  "logInScreen_login_email_usernameForm": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 10,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 4,
    "borderTopRightRadius": 4,
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 4,
    "textAlign": "left",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontFamily": "Segoe UI",
    "width": "100.00%",
    "height": 41,
    "left": 0,
    "bottom": 0
  },
  "logInScreen_login_email_usernameForm_emailContainer": {
    "opacity": 1,
    "position": "absolute",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "borderTopLeftRadius": 4,
    "borderTopRightRadius": 4,
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 4,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_email_usernameForm_text": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 345,
    "height": 46,
    "right": 5,
    "bottom": 12.5
  },
  "logInScreen_login_email_usernameForm_pleaseEnterYourEmail": {
    "opacity": 0.550000011920929,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 16,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 36,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 189,
    "height": 51,
    "left": 10,
    "bottom": 7.5
  },
  "logInScreen_login_defaultButton": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 351,
    "height": 57,
    "left": 308.99,
    "top": 677.99
  },
  "logInScreen_login_defaultButton_buttonLogin": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "100.00%",
    "height": "100.00%",
    "left": 0,
    "top": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_activeState": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_activeState_rectangle1515": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(118, 204, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 8,
    "borderTopRightRadius": 8,
    "borderBottomLeftRadius": 8,
    "borderBottomRightRadius": 8,
    "width": 351,
    "height": 57,
    "left": 0,
    "top": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_activeState_signIn": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(255, 255, 255, 1)",
    "fontSize": 21,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 23.1,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 79,
    "height": 28,
    "left": 133,
    "top": 22
  },
  "logInScreen_login_defaultButton_buttonLogin_hoverState": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_hoverState_rectangle1515ac505faa": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 8,
    "borderTopRightRadius": 8,
    "borderBottomLeftRadius": 8,
    "borderBottomRightRadius": 8,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_hoverState_signIn177c060f": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(2, 106, 167, 1)",
    "fontSize": 21,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 23.1,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 74,
    "height": 28,
    "left": 133,
    "bottom": 7
  },
  "logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da_rectangle1515f416294e": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(2, 106, 167, 1)",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "borderTopWidth": 1,
    "borderTopColor": "rgba(112, 112, 112, 1)",
    "borderRightWidth": 1,
    "borderRightColor": "rgba(112, 112, 112, 1)",
    "borderBottomWidth": 1,
    "borderBottomColor": "rgba(112, 112, 112, 1)",
    "borderLeftWidth": 1,
    "borderLeftColor": "rgba(112, 112, 112, 1)",
    "borderTopLeftRadius": 8,
    "borderTopRightRadius": 8,
    "borderBottomLeftRadius": 8,
    "borderBottomRightRadius": 8,
    "width": "auto",
    "height": "auto",
    "left": 0,
    "top": 0,
    "right": 0,
    "bottom": 0
  },
  "logInScreen_login_defaultButton_buttonLogin_buttonLogin2ead04da_signIn3d82c550": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(255, 255, 255, 1)",
    "fontSize": 21,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 23.1,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 79,
    "height": 28,
    "left": 133,
    "bottom": 7
  },
  "logInScreen_login_staySignedInBox": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "transparent",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 115,
    "height": 21,
    "left": 269.99,
    "top": 549.99
  },
  "logInScreen_login_forgotYourPassword": {
    "opacity": 0.75,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 18,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 41,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 182,
    "height": 47,
    "left": 532.99,
    "top": 538.49
  }
});