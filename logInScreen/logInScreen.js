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
        <ReactImage data-layer="aa58faba-155f-4fd4-ba95-0cd11b4e8b86" source={require('./assets/nserl1366.png')} style={styles.logInScreen_nserl1366} />
        <View data-layer="761bd75c-f22e-4c3e-9400-e9e007832aa2" style={styles.logInScreen_login}>
            <View data-layer="89ad4acb-c23c-47e4-9dc9-3a6489cf38da" style={styles.logInScreen_login_bg}></View>
            <View data-layer="799929aa-1c6f-4d64-8058-e403dde49ff6" style={styles.logInScreen_login_showhide}>
                <View data-layer="9371579a-edb0-4ac1-91ac-5df026a3dfa7" style={styles.logInScreen_login_showhide_rectangle1489}></View>
                <View data-layer="6b400ea6-01b1-4262-a544-d44101c531b6" style={styles.logInScreen_login_showhide_iconFeatherEyeOff}>
                    <Svg data-layer="c1e3047a-0bb7-45b4-966e-871e94720032" style={styles.logInScreen_login_showhide_iconFeatherEyeOff_path805} preserveAspectRatio="none" viewBox="-0.3333335220813751 1.6666497886180878 16.666748106479645 12.666503965854645" fill="transparent"><SvgPath d="M 11.96000003814697 11.96000003814697 C 10.82039260864258 12.82866764068604 9.432737350463867 13.30990791320801 7.999999046325684 13.33333206176758 C 3.333333492279053 13.33333301544189 0.6666665077209473 7.999999523162842 0.6666665077209473 7.999999523162842 C 1.495927810668945 6.454596996307373 2.646090507507324 5.104404449462891 4.040000915527344 4.039999961853027 M 6.599999904632568 2.826666593551636 C 7.058887958526611 2.719253063201904 7.528708934783936 2.665558815002441 7.999999523162842 2.666666507720947 C 12.66666603088379 2.666666746139526 15.33333396911621 8 15.33333396911621 8 C 14.92865371704102 8.757071495056152 14.44603729248047 9.46982479095459 13.89333343505859 10.12666702270508 M 9.413333892822266 9.413333892822266 C 8.91474437713623 9.948406219482422 8.163851737976074 10.16866111755371 7.455230712890625 9.987691879272461 C 6.746609687805176 9.806721687316895 6.193277835845947 9.253390312194824 6.012308120727539 8.544768333435059 C 5.831337928771973 7.836146831512451 6.051592826843262 7.085254669189453 6.586666584014893 6.586666107177734"  /></Svg>
                    <Svg data-layer="4bda2cb8-a2a0-4968-8ece-687dc7751983" style={styles.logInScreen_login_showhide_iconFeatherEyeOff_path806} preserveAspectRatio="none" viewBox="-0.3333333432674408 -0.3333333432674408 16.666748106479645 16.666503965854645" fill="transparent"><SvgPath d="M 0.6666666865348816 0.6666666865348816 L 15.33333301544189 15.33333301544189"  /></Svg>
                </View>
            </View>
            <View data-layer="abecb13f-9466-4b9d-8307-429a89615f6c" style={styles.logInScreen_login_email8b6b7e4d}>
                <Text data-layer="206c5c5b-235b-494b-8734-0434d20387ef" style={styles.logInScreen_login_email8b6b7e4d_password2646f71c}>Password</Text>
                <View data-layer="2a0deee4-1a1a-4da2-ac30-35a11738b146" style={styles.logInScreen_login_email8b6b7e4d_inputBox45703c1a}></View>
                <Text data-layer="8e7bbd2a-9644-49bd-b0e1-8a55b53a29cd" style={styles.logInScreen_login_email8b6b7e4d_inputc9bc4c2a}>Input</Text>
                <Text data-layer="81029ad6-6f6d-40c3-9054-5e676c271578" style={styles.logInScreen_login_email8b6b7e4d_password}>Password</Text>
            </View>
            <Text data-layer="4356fe86-b375-4ccf-9c0c-60a2df3152b2" style={styles.logInScreen_login_welcomeBack}>Welcome back!</Text>
            <View style={styles.logInScreen_login_logIn}><Text data-layer="029230d7-ec61-4a5f-a801-ab28cdbc0b8d" style={{"marginTop":-9,"color":"rgba(0, 0, 0, 1)","fontSize":54,"fontWeight":"700","fontStyle":"normal","fontFamily":"Segoe UI","textAlign":"left","lineHeight":59.400000000000006}}>Log In</Text></View>
            <View data-layer="5e267a9d-1403-42cd-a7c2-d854f66127d4" style={styles.logInScreen_login_name}>
                <Text data-layer="0018a27b-0808-494e-ab36-c19ce9bc0973" style={styles.logInScreen_login_name_email}>Email</Text>
                <View data-layer="b1e126eb-e8e1-4908-99f2-37a642099663" style={styles.logInScreen_login_name_inputBox}></View>
                <Text data-layer="46025d60-8a8c-4829-b733-7d9cb01c2f7b" style={styles.logInScreen_login_name_input}>Input</Text>
            </View>
            <View data-layer="995a5873-23b6-4229-a0ed-be576754be3d" style={styles.logInScreen_login_defaultButton}>
                <View data-layer="877ac06b-f063-4ab1-9303-4066092fb504" style={styles.logInScreen_login_defaultButton_rectangle1515}></View>
                <View style={styles.logInScreen_login_defaultButton_signIn}><Text data-layer="3188fc55-5928-4389-9f12-78fd59959b31" style={{"marginTop":-3.5,"color":"rgba(255, 255, 255, 1)","fontSize":21,"fontWeight":"700","fontStyle":"normal","fontFamily":"Segoe UI","textAlign":"left","lineHeight":23.1}}>SIGN IN</Text></View>
            </View>
        </View>
        <View data-layer="d6493c66-7964-4839-a791-8ed2b7925175" style={styles.logInScreen_blueOpaqueCircle}></View>
        <ReactImage data-layer="ae6ded04-7025-4eb6-9a8a-288d1922d29b" source={require('./assets/header.png')} style={styles.logInScreen_header} />
        <Text data-layer="8b80938c-35bd-47f3-82d5-d93f9dd2b158" style={styles.logInScreen_forgotYourPassword9d3a78d3}>Forgot your password?</Text>
        <Text data-layer="24a3ff00-60dd-4ed1-b8ee-bacf9c5f8fa7" style={styles.logInScreen_forgotYourPassword}>Forgot your password?</Text>
        <View data-layer="8452b4af-c6e4-4a72-9f63-4b4958da16e1" style={styles.logInScreen_staySignedInBox}>
            <View data-layer="a1aeb1af-57c2-48e2-a2c7-ed796dee09d3" style={styles.logInScreen_staySignedInBox_rectangle1516}></View>
            <Text data-layer="155f39ac-7b2b-402d-8c36-4ed136644858" style={styles.logInScreen_staySignedInBox_staySignedIn}>Stay Signed In</Text>
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
    "width": 1920,
    "height": 1080,
    "left": 0,
    "top": 0
  },
  "logInScreen_nserl1366": {
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
    "width": 1986,
    "height": 1117,
    "left": -66,
    "top": -18
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
    "width": 1149,
    "height": 1099,
    "top": -9,
    "right": -189
  },
  "logInScreen_login_bg": {
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
    "borderTopLeftRadius": 0,
    "borderTopRightRadius": 0,
    "borderBottomLeftRadius": 0,
    "borderBottomRightRadius": 0,
    "width": 1149,
    "height": 1099,
    "left": 0,
    "top": 0
  },
  "logInScreen_login_showhide": {
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
    "width": 16,
    "height": 16,
    "left": 631,
    "top": 466
  },
  "logInScreen_login_showhide_rectangle1489": {
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
    "borderTopLeftRadius": 0,
    "borderTopRightRadius": 0,
    "borderBottomLeftRadius": 0,
    "borderBottomRightRadius": 0,
    "width": 16,
    "height": 16,
    "left": 0,
    "top": 0
  },
  "logInScreen_login_showhide_iconFeatherEyeOff": {
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
    "width": 14.67,
    "height": 14.67,
    "left": 1,
    "top": 1
  },
  "logInScreen_login_showhide_iconFeatherEyeOff_path805": {
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
    "width": 17.33,
    "height": 13.33,
    "left": -1.33,
    "top": 0.67
  },
  "logInScreen_login_showhide_iconFeatherEyeOff_path806": {
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
    "width": 17.33,
    "height": 17.33,
    "left": -1.33,
    "top": -1.33
  },
  "logInScreen_login_email8b6b7e4d": {
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
    "height": 81,
    "left": 305,
    "top": 413
  },
  "logInScreen_login_email8b6b7e4d_password2646f71c": {
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
  "logInScreen_login_email8b6b7e4d_inputBox45703c1a": {
    "opacity": 0.36000001430511475,
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
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 8,
    "width": 351,
    "height": 46,
    "left": 0,
    "top": 35
  },
  "logInScreen_login_email8b6b7e4d_inputc9bc4c2a": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(196, 196, 196, 1)",
    "fontSize": 14,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 21,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 33,
    "height": 26,
    "left": 12,
    "top": 47
  },
  "logInScreen_login_email8b6b7e4d_password": {
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
  "logInScreen_login_welcomeBack": {
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
    "left": 399,
    "top": 214
  },
  "logInScreen_login_logIn": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 54,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 59.400000000000006,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 159,
    "height": 72,
    "left": 401,
    "top": 145
  },
  "logInScreen_login_name": {
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
    "height": 81,
    "left": 305,
    "top": 286
  },
  "logInScreen_login_name_email": {
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
  "logInScreen_login_name_inputBox": {
    "opacity": 0.36000001430511475,
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
    "borderBottomLeftRadius": 4,
    "borderBottomRightRadius": 8,
    "width": 351,
    "height": 46,
    "left": 0,
    "top": 35
  },
  "logInScreen_login_name_input": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(196, 196, 196, 1)",
    "fontSize": 14,
    "fontWeight": "400",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "left",
    "lineHeight": 21,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 33,
    "height": 26,
    "left": 12,
    "top": 47
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
    "left": 305,
    "top": 700
  },
  "logInScreen_login_defaultButton_rectangle1515": {
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
    "width": 351,
    "height": 57,
    "left": 0,
    "top": 0
  },
  "logInScreen_login_defaultButton_signIn": {
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
  "logInScreen_blueOpaqueCircle": {
    "opacity": 0.33000001311302185,
    "position": "absolute",
    "backgroundColor": "rgba(105, 147, 160, 1)",
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
    "borderTopLeftRadius": 0,
    "borderTopRightRadius": 0,
    "borderBottomLeftRadius": 0,
    "borderBottomRightRadius": 0,
    "width": 977,
    "height": 1099,
    "left": -21,
    "top": -9
  },
  "logInScreen_header": {
    "opacity": 1,
    "position": "absolute",
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "width": 600,
    "height": 97,
    "left": 194,
    "top": 447
  },
  "logInScreen_header_copy": {
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
    "width": 598,
    "height": 97,
    "left": 0,
    "top": 0
  },
  "logInScreen_header_copy_didYouKnow": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(255, 255, 255, 1)",
    "fontSize": 42,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Segoe UI",
    "textAlign": "center",
    "lineHeight": 54,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 292,
    "height": 68,
    "left": 170,
    "top": 1
  },
  "logInScreen_header_copy_cloudEnsembleWasOriginallyCalledPages": {
    "opacity": 1,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(255, 255, 255, 1)",
    "fontSize": 24,
    "fontWeight": "700",
    "fontStyle": "normal",
    "fontFamily": "Montserrat",
    "textAlign": "center",
    "lineHeight": 26.400000000000002,
    "marginTop": 0,
    "marginRight": 0,
    "marginBottom": 0,
    "marginLeft": 0,
    "paddingTop": 0,
    "paddingRight": 0,
    "paddingBottom": 0,
    "paddingLeft": 0,
    "width": 598,
    "height": 29,
    "left": 0,
    "top": 72
  },
  "logInScreen_forgotYourPassword9d3a78d3": {
    "opacity": 0.75,
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
    "width": 162,
    "height": 41,
    "left": 1454,
    "top": 532.5
  },
  "logInScreen_forgotYourPassword": {
    "opacity": 0.75,
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
    "width": 162,
    "height": 41,
    "left": 1454,
    "top": 532.5
  },
  "logInScreen_staySignedInBox": {
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
    "width": 110,
    "height": 21,
    "left": 1267,
    "top": 540
  },
  "logInScreen_staySignedInBox_rectangle1516": {
    "opacity": 0.6000000238418579,
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
    "borderTopLeftRadius": 0,
    "borderTopRightRadius": 0,
    "borderBottomLeftRadius": 0,
    "borderBottomRightRadius": 0,
    "width": 21,
    "height": 21,
    "left": 0,
    "top": 0
  },
  "logInScreen_staySignedInBox_staySignedIn": {
    "opacity": 0.75,
    "position": "absolute",
    "backgroundColor": "rgba(255, 255, 255, 0)",
    "color": "rgba(0, 0, 0, 1)",
    "fontSize": 12,
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
    "width": 75,
    "height": 40,
    "left": 35,
    "top": -7
  }
});