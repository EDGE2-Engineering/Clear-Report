
import React from 'react';
import { Link } from 'react-router-dom';
import { MapPin, Phone, Mail, ClipboardCheck } from 'lucide-react';
const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white py-10 border-t border-gray-800">
      <div className="container mx-auto px-4">
        <div className="pt-0 flex flex-col md:flex-row justify-between items-center text-xs text-gray-500">
          <p>&copy; {new Date().getFullYear()} {"EDGE2 Engineering Solutions India Pvt. Ltd."}. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
