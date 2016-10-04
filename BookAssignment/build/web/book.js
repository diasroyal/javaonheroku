  // remove string's leading spaces, then trailing spaces
  String.prototype.trim = function () {  
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
  }

  function dataCheck(form) {
    widget = form.tf_bookName;
    if (widget.value.trim() == "") {
      alert("You must enter a Book Name");
      widget.focus();
      widget.select();
      return false;
    }
    widget = form.tf_author;
    if (widget.value.trim() == "") {
      alert("You must enter Author");
      widget.focus();
      widget.select();
      return false;
    }
     widget = form.tf_isbn;
    if (widget.value.trim() == "") {
      alert("You must enter ISBN");
      widget.focus();
      widget.select();
      return false;
    }
     widget = form.tf_price;
    if (widget.value.trim() == "") {
      alert("You must enter Price");
      widget.focus();
      widget.select();
      return false;
    }

    return true;
  }
