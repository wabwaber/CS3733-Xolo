'use strict';

class TextBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: this.props.commentID }
  }

  render() {
    return React.createElement(
        'input',
        { type: 'text', onChange: function(syntheticEvent) {
            console.log(syntheticEvent.target.value)}
        }
     );
    }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.text_box')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(TextBox, { commentID: commentID }),
      domContainer
    );
  });