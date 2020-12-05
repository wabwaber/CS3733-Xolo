'use strict';

class ChoiceName extends React.Component {
    constructor(props) {
        super(props);
        this.state = { choice_name: "", choice_desc: "" };
    }

    render() {
        var data = {};

        const urlParams = new URLSearchParams(window.location.search);
        data["choiceID"] = urlParams.get('id');

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", get_choice_url, false);
        xhr.send(js);

        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                this.state.choice_name = js["choice"]["name"];
                this.state.choice_desc = js["choice"]["description"];
            }
        }
                
        return (
            <div>
                <div className="name">
                    <h2>{"Choice: " + this.state.choice_name}</h2>
                </div>
                <div className="desc">
                    <p>{"Description: " + this.state.choice_desc}</p>
                </div>  
            </div>
        )
    }
}

document.querySelectorAll('.choice_name')
    .forEach(domContainer => {
        ReactDOM.render(
        React.createElement(ChoiceName, { }),
        domContainer
        );
    });