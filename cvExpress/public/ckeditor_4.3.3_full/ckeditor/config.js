/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	config.language =  "zh-cn" ;
	// config.uiColor = '#AADC6E';
config.toolbar = [
    [ 'Bold', 'Italic','Underline','-','NumberedList','BulletedList','-',
'Image','Iframe', 'EqnEditor']
];
config.extraPlugins = 'eqneditor';
config.resize_enabled = false;
config.removePlugins = 'elementspath';
config.shiftEnterMode = CKEDITOR.ENTER_BR;
config.enterMode = CKEDITOR.ENTER_BR;
};
